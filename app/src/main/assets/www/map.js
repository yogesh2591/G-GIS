var olmap = new ol.Map({
		  target: 'map',
		  layers: [
			new ol.layer.Tile({
				source: new ol.source.OSM({
					url: 'http://mt{0-3}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}',
					attributions: [
						new ol.Attribution({ html: '� Google' }),
						new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
					]
				})
			})
		  ],
		  view: new ol.View({
			center: ol.proj.transform(
				[72.821807,18.974611], 'EPSG:4326', 'EPSG:3857'),
			zoom: 5
		  })
		});
		
		var getMapBounds = function(){
			return olmap.getView().calculateExtent(olmap.getSize());
		}
		
		var zoomtoGPS = function(lat,lang){
//			var lat = 19.0760;
//			var long = 72.8777;
			console.log("Long: " + lang + " Lat: " + lat);
			olmap.getView().setCenter(ol.proj.transform([lang, lat], 'EPSG:4326', 'EPSG:3857'));
			olmap.getView().setZoom(20);

			



		}