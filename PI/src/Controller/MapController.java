/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author MedAmine
 */
public class MapController implements Initializable, MapComponentInitializedListener{
@FXML
    private GoogleMapView mapView;

    @FXML
    private ImageView aboutimg;    
    private GoogleMap map;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         mapView.addMapInializedListener(this);
         
         Image image = new Image(getClass().getResource("/Image/about.png").toExternalForm());
            aboutimg.setImage(image);
    }

    @Override
    public void mapInitialized() {
        LatLong Esprit = new LatLong(36.8992677,10.1895256);
        LatLong quincaillerie1 = new LatLong(36.874255,10.194832);
        LatLong quincaillerie2 = new LatLong(36.8914792,10.1526152);
        LatLong quincaillerie3 = new LatLong(36.8871677,10.2172337);
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.8403528,10.1385998))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);

        //Add markers to the map
       MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(Esprit);
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(quincaillerie1);
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(quincaillerie2);
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(quincaillerie3);
        
       
        
        Marker Espritp = new Marker(markerOptions1);
        Marker Quinc1 = new Marker(markerOptions2);
        Marker Quinc2 = new Marker(markerOptions3);
        Marker Quinc3 = new Marker(markerOptions4);
        
        
        map.addMarker( Espritp );
        map.addMarker( Quinc1 );
        map.addMarker( Quinc2 );
        map.addMarker( Quinc3 );
       
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h7>Esprit</h7>");
        InfoWindowOptions infoWindowOptions1 = new InfoWindowOptions();
            infoWindowOptions1.content("<h7>Quincaillerie \"J'aime Ma Maison\"</h7>");
        InfoWindowOptions infoWindowOptions2 = new InfoWindowOptions();
            infoWindowOptions2.content("<h7>Quincaillerie El Ghazela</h7>");
        InfoWindowOptions infoWindowOptions3 = new InfoWindowOptions();
            infoWindowOptions3.content("<h7>Laquimco</h7>");
        
        
        InfoWindow espritInfoWindow = new InfoWindow(infoWindowOptions);
        InfoWindow q1InfoWindow = new InfoWindow(infoWindowOptions1);
        InfoWindow q2InfoWindow = new InfoWindow(infoWindowOptions2);
        InfoWindow q3InfoWindow = new InfoWindow(infoWindowOptions3);
        
        
        
        espritInfoWindow.open(map, Espritp);
        q1InfoWindow.open(map, Quinc1);
        q2InfoWindow.open(map, Quinc2);
        q3InfoWindow.open(map, Quinc3);
        
    }
    
}
