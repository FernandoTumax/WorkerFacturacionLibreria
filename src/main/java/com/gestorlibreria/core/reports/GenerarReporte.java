package com.gestorlibreria.core.reports;

import com.gestorlibreria.core.db.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

import java.util.HashMap;
import java.util.Map;

public class GenerarReporte {

    private static GenerarReporte instances = null;

    public static GenerarReporte getInstances(){
        if(instances == null){
            instances = new GenerarReporte();
        }
        return instances;
    }

    public GenerarReporte(){

    }

    public void generar(String idPedido){
        String sourceFileName =
                "C:\\Users\\ferna\\IdeaProjects\\WorkerGestorLibreria\\src\\main\\java\\com\\gestorlibreria\\core\\reports\\FacturaGestorLibreria.jasper";
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", "C:\\Users\\ferna\\IdeaProjects\\WorkerGestorLibreria\\src\\main\\java\\com\\gestorlibreria\\core\\reports");
        parameters.put("_idPedido", idPedido);
        try{
            JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReportToFile(
                    sourceFileName, parameters, Conexion.getInstancia().getConexion()),
                    "C:\\Reporte\\FacturacionGestorLibreria" + ".pdf");
        }catch (JRException e){
            e.printStackTrace();
        }
    }

}
