package com.gestorlibreria.core.workerqueues;

import com.gestorlibreria.core.beans.Root;
import com.gestorlibreria.core.entitys.*;
import com.gestorlibreria.core.manejadores.ManejadorDeCorreo;
import com.gestorlibreria.core.reports.GenerarReporte;
import com.gestorlibreria.core.services.*;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Worker {
    @Autowired
    private IRootService facturaService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private ISchoolStoreService schoolStoreService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IFacturaProductService facturaProductService;

    final static Logger logger = Logger.getLogger(Worker.class);
    @Value("localhost")
    private String host;
    @Value("5672")
    private int port;
    @Value("guest")
    private String username;
    @Value("guest")
    private String password;
    @Value("/facturacionVH")
    private String virtualHost;
    @Value("FacturacionManagementQueue")
    private String queue;

    public void proccessWork() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(this.host);
        connectionFactory.setPort(this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(this.queue, true, false, false, null);
        logger.debug("ProccessWork <<< [*] waiting for message (to exit press CTRL+C)");
        channel.basicQos(1);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            logger.debug("proccessWork <<< [x] received ==>".concat(message).concat(" <=="));
            try{
                System.out.println(message);
                com.gestorlibreria.core.beans.Root rootJson = new Gson().fromJson(message, com.gestorlibreria.core.beans.Root.class);
                com.gestorlibreria.core.entitys.Root nuevaFactura = new com.gestorlibreria.core.entitys.Root();
                System.out.println(nuevaFactura);
                System.out.println(rootJson);
                for(com.gestorlibreria.core.beans.Product elemento : rootJson.getProducts()){
                    Product producto = productService.findById(elemento.get_id());
                    if(producto == null){
                        Product nuevo = new Product();
                        nuevo.set_id(elemento.get_id());
                        nuevo.setName(elemento.getName());
                        nuevo.setDescription(elemento.getDescription());
                        nuevo.setPrice(elemento.getPrice());
                        nuevo.setStock(elemento.getStock());
                        nuevo.setImg(elemento.getImg());
                        nuevo.setCantidad(elemento.getCantidad());
                        producto = productService.save(nuevo);
                        System.out.println("Agregando Producto a la base de datos");
                    }
                    FacturaProduct facturaProduct = new FacturaProduct();
                    facturaProduct.setProduct(producto);
                    facturaProduct.setRoot(nuevaFactura);
                    nuevaFactura.getProducts().add(facturaProduct);
                }
                System.out.println("Finalizando products");
                SchoolStore schoolStore = schoolStoreService.findById(rootJson.getSchoolStore().get_id());
                if(schoolStore == null){
                    SchoolStore nuevo = new SchoolStore();
                    nuevo.set_id(rootJson.getSchoolStore().get_id());
                    nuevo.setNameSchoolStore(rootJson.getSchoolStore().getNameSchoolStore());
                    nuevo.setDirectionSchoolStore(rootJson.getSchoolStore().getDirectionSchoolStore());
                    nuevo.setNIT(rootJson.getSchoolStore().getNIT());
                    schoolStore =schoolStoreService.save(nuevo);
                }
                nuevaFactura.setSchoolStore(schoolStore);
                System.out.println("Finalizando schoolStore");
                Client cliente = clientService.findById(rootJson.getClient().get_id());
                if(cliente == null){
                    Client nuevo = new Client();
                    nuevo.set_id(rootJson.getClient().get_id());
                    nuevo.setNombreCliente(rootJson.getClient().getNombreCliente());
                    nuevo.setApellidoCliente(rootJson.getClient().getApellidoCliente());
                    nuevo.setEmailCliente(rootJson.getClient().getEmailCliente());
                    cliente = clientService.save(nuevo);
                }
                nuevaFactura.setClient(cliente);
                nuevaFactura.set_id(rootJson.get_id());
                nuevaFactura.setFecha(rootJson.getFecha());
                nuevaFactura.setTotalAPagar(rootJson.getTotalAPagar());
                System.out.println("Finalizando Clients");
                System.out.println(rootJson.get_id());
                if(!facturaService.existsById(nuevaFactura.get_id())){
                    System.out.println(nuevaFactura);
                    facturaService.save(nuevaFactura);
                }
                for(FacturaProduct elemento : nuevaFactura.getProducts()){
                    FacturaProductFK facturaProductFK = new FacturaProductFK();
                    facturaProductFK.setProductId(elemento.getProduct().get_id());
                    facturaProductFK.setRootId(nuevaFactura.get_id());
                    FacturaProduct facturaProduct = new FacturaProduct();
                    facturaProduct.setFacturaProductFK(facturaProductFK);
                    facturaProductService.save(facturaProduct);
                    System.out.println(facturaProduct);
                }
                System.out.println("Finalizando facturas");
                doWork(message);
                GenerarReporte.getInstances().generar(nuevaFactura.get_id());
                try{
                    System.out.println(rootJson.getClient().getEmailCliente());
                    ManejadorDeCorreo.getInstancia().enviarCorreoRegistro("Factura de confirmacion de pedido en: TusUtilesEscolares.com", "Factura No.".concat(nuevaFactura.get_id()), "Aqui esta tu pedido de la libreria ".concat(rootJson.getSchoolStore().getNameSchoolStore()), "muchas gracias cliente ".concat(rootJson.getClient().getNombreCliente().concat("Por contar con nosotros")), rootJson.getClient().getEmailCliente());
                    //ManejadorDeCorreo.getInstancia().enviarCorreoRegistro("Factura de confirmacion para pedido en: ".concat(nuevaFactura.getSchoolStore().getNameSchoolStore()), "Factura No.".concat(nuevaFactura.get_id()), "Aqui esta tu pedido en la libreria ".concat(rootJson.getSchoolStore().getNameSchoolStore()), "Muchas gracias cliente ".concat(rootJson.getClient().getNombreCliente()).concat("por contar con nuestro servicio."), rootJson.getClient().getEmailCliente());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }finally {
                System.out.println("proccessWork <<< [x] done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(this.queue, false, deliverCallback, consumerTag -> {
            System.out.println("done");
        });
    }

    public static void doWork(String task){
        int i = 0;
        for (char letter : task.toCharArray()){
            if(letter == '.'){
                try{
                    System.out.println("doWork <<< working ".concat(String.valueOf(i++)).concat("..."));
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("doWork <<< done!!!");
    }

}
