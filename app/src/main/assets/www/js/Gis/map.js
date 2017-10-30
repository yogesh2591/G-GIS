Map = (function () {
	let scaleLineControl = new ol.control.ScaleLine();
	let hybrid_map_layer = new ol.layer.Tile({
		source: new ol.source.OSM({
			url: 'http://mt{0-3}.google.com/vt/lyrs=y&x={x}&y={y}&z={z}',
			attributions: [
				new ol.Attribution({ html: '� Google' }),
				new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
			]
		}),
		visible: false,
		name: "hybrid_map_layer"
	});
	let standard_map_layer = new ol.layer.Tile({
		source: new ol.source.OSM({

			url: 'http://mt{0-3}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}',
			attributions: [
				new ol.Attribution({ html: '� Google' }),
				new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
			]
		}),
		name: "standard_map_layer",
		visible: false
	});
	let open_layer = new ol.layer.Tile({
		source: new ol.source.OSM(),
		visible: true,
		name: "open_layer"
	})
	let layers = [open_layer, hybrid_map_layer, standard_map_layer];
	let olmap = new ol.Map({
		controls: ol.control.defaults({
			attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
				collapsible: false
			})
		}).extend([
			scaleLineControl
		]),
		target: 'map',
		layers: layers,
		view: new ol.View({
			center: ol.proj.transform(
				[72.821807, 18.974611], 'EPSG:4326', 'EPSG:3857'),
			zoom: 5,
			minZoom: 4,
			maxZoom: 25
		})
	});

	return {
		zoomIn: function () {
			let zoomLevel = olmap.getView().getZoom() + 1;
			let view = olmap.getView();
			view.animate({
				zoom: zoomLevel,
				duration: 1000
			});
		},
		zoomOut: function () {
			let zoomLevel = olmap.getView().getZoom() - 1;
			let view = olmap.getView();
			view.animate({
				zoom: zoomLevel,
				duration: 1000
			});
		},
		getCurrentZoom: function () {
			return olmap.getView().getZoom();
		},
		getMapExtent: function () {
			return ol.proj.transformExtent((olmap.getView().calculateExtent(olmap.getSize())), 'EPSG:3857', 'EPSG:4326');

		},
		resetNorth: function () {
			olmap.getView().setRotation(0);
		},
		zoomtoGPS: function (lat, lang) {
			try{
					olmap.getLayers().forEach(function(itemLayer){
						{
							if(itemLayer.get('name')==="location_layer"){
								olmap.removeLayer(itemLayer);
								console.log("removing Location Layer ");
								
							}else{
								
							}
						}
					});
			}catch(err){console.log(err);}
			var all_layer = olmap.getLayers();
			var location_icon = new ol.Feature({
				geometry: new ol.geom.Point(ol.proj.transform([lang, lat], 'EPSG:4326', 'EPSG:3857'))
			});

			var iconStyle = new ol.style.Style({
				image: new ol.style.Icon(/** @type {olx.style.IconOptions} */({
					anchor: [0.5, 46],
					anchorXUnits: 'fraction',
					anchorYUnits: 'pixels',
					opacity:1,
					src: 'img/location_marker.png'
				}))
			});

			location_icon.setStyle(iconStyle);

			var vectorSource = new ol.source.Vector({
				features: [location_icon]
			});

			var vectorLayer = new ol.layer.Vector({
				source: vectorSource,
				name: "location_layer"
			});

			olmap.addLayer(vectorLayer);
			console.log("adding Location Layer");
			let zoomLevel = 19;
			let center = ol.proj.transform([lang, lat], 'EPSG:4326', 'EPSG:3857');
			let view = olmap.getView();
			view.animate({
				center: center,
				zoom: zoomLevel,
				duration: 3000

			});
			setTimeout(function(){
				try{
					olmap.getLayers().forEach(function(itemLayer){
						{
							if(itemLayer.get('name')==="location_layer"){
								olmap.removeLayer(itemLayer);
								console.log("removing Location Layer ");
								
							}else{
								
							}
						}
					});
			}catch(err){console.log(err);}
			},1000*60*2);


		},
		setBaseMap: function (base_layer_code) {
			olmap.getLayers().forEach(function (layer, i) {
				if (layer instanceof ol.layer.Group || layer instanceof ol.layer.Vector) {
					layer.setVisible(true);
				}
				else if (i == base_layer_code) {
					layer.setVisible(true);
				} else {
					layer.setVisible(false);
				}

			});

		},
		getAllLayersFromMap: function () {
			return olmap.getLayers();
		},
		getScaleLineControl: function () {
			return scaleLineControl;
		},
		getMap: function () {
			return olmap;
		}
	}

}());
