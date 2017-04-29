var hybrid_map_layer = new ol.layer.Tile({
                       				source: new ol.source.OSM({
                       					url: 'http://mt{0-3}.google.com/vt/lyrs=y&x={x}&y={y}&z={z}',
                       					attributions: [
                       						new ol.Attribution({ html: '� Google' }),
                       						new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
                       					]
                       				})
                       			});
var standard_map_layer = new ol.layer.Tile({
                       				source: new ol.source.OSM({
                       					url: 'http://mt{0-3}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}',
                       					attributions: [
                       						new ol.Attribution({ html: '� Google' }),
                       						new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
                       					]
                       				})
                       			});
//var terrain_map_layer = new ol.layer.Tile({
//                       				source: new ol.source.OSM({
//                       					url: 'http://mt{0-3}.google.com/vt/lyrs=p&x={x}&y={y}&z={z}',
//                       					attributions: [
//                       						new ol.Attribution({ html: '� Google' }),
//                       						new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
//                       					]
//                       				})
//                       			});
//var arc_gis_layer  = new ol.layer.Tile({
//                                        source: new ol.source.TileArcGISRest({
//                                            url: "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"
//                                          })
//                                        });
var open_layer = new ol.layer.Tile({
            source: new ol.source.OSM()
          })

var olmap = new ol.Map({
		  target: 'map',
		  layers: [
				open_layer,hybrid_map_layer,standard_map_layer
		  ],
		  view: new ol.View({
			center: ol.proj.transform(
				[72.821807,18.974611], 'EPSG:4326', 'EPSG:3857'),
			zoom: 5,
			minZoom : 4 ,
			maxZoom : 25
		  })
		});

		var getMapBounds = function(){

			return olmap.getView().calculateExtent(olmap.getSize());
		}

		var zoomtoGPS = function(lat,lang){
          olmap.getView().setCenter(ol.proj.transform([lang, lat], 'EPSG:4326', 'EPSG:3857'));
           olmap.getView().setZoom(20);
		}

		var zoomIn = function(){

		    olmap.getView().setZoom(olmap.getView().getZoom() + 1 );
		}

		var zoomOut = function(){

		olmap.getView().setZoom(olmap.getView().getZoom() -  1 );
		}

		var resetNorth =function(){

		    olmap.getView().setRotation(0);
		}

		var setMapLayerVisibility = function (layer_code)
	    {
            switch(layer_code)
            {
                case  0 :
                    hybrid_map_layer.setVisible(true);
                    standard_map_layer.setVisible(false);
                    open_layer.setVisible(false);
                break;
                case  1 :
                    hybrid_map_layer.setVisible(false);
                    standard_map_layer.setVisible(true);
                    open_layer.setVisible(false);
                break;
                case  2 :
                    hybrid_map_layer.setVisible(false);
                    standard_map_layer.setVisible(false);
                    open_layer.setVisible(true);
                break;
            }
		 }