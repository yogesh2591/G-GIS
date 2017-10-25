Map = (function () {
	let scaleLineControl = new ol.control.ScaleLine();
	let hybrid_map_layer = new ol.layer.Tile({
		source: new ol.source.OSM({
			url: 'http://mt{0-3}.google.com/vt/lyrs=y&x={x}&y={y}&z={z}',
			attributions: [
				new ol.Attribution({ html: '� Google' }),
				new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
			]
		})
	});
	let standard_map_layer = new ol.layer.Tile({
		source: new ol.source.OSM({
			url: 'http://mt{0-3}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}',
			attributions: [
				new ol.Attribution({ html: '� Google' }),
				new ol.Attribution({ html: '<a href="https://developers.google.com/maps/terms">Terms of Use.</a>' })
			]
		})
	});
	let open_layer = new ol.layer.Tile({
		source: new ol.source.OSM()
	})
	let layers = [open_layer, hybrid_map_layer, standard_map_layer ];
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
				duration : 1000
			});
		},
		zoomOut: function () {
			let zoomLevel = olmap.getView().getZoom() - 1;
			let view = olmap.getView();
			view.animate({
				zoom: zoomLevel,
				duration : 1000
			});
		},
		getCurrentZoom: function () {
			return olmap.getView().getZoom();
		},
		getMapExtent: function () {
			//return olmap.getView().calculateExtent(olmap.getSize());
			return ol.proj.transform((olmap.getView().calculateExtent(olmap.getSize())), 'EPSG:3857', 'EPSG:4326');

		},
		resetNorth: function () {
			olmap.getView().setRotation(0);
		},
		zoomtoGPS: function (lat, lang) {
			let zoomLevel = 20;
			let center = ol.proj.transform([lang, lat], 'EPSG:4326', 'EPSG:3857');
			let view = olmap.getView();
			view.animate({
				center:center,
				zoom: zoomLevel,
				duration : 3000
				
			});
		},
		setBaseMap: function (base_layer_code) {
			olmap.getLayers().forEach(function (layer, i) {
				if (layer instanceof ol.layer.Group) {
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
