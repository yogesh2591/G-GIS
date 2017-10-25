var zoomIn = function(){
     Map.zoomIn();
}
var zoomOut = function(){
Map.zoomOut();
}
var resetNorth =function(){
Map.resetNorth();
}
var setMapLayerVisibility = function (layer_code)
{
Map.setBaseMap(layer_code);
}
var zoomtoGPS = function(lat,lang){
          Map.zoomtoGPS(lat,lang)
		}
var layer_group = new ol.layer.Group({
    layers : [new ol.layer.Tile({
        source: new ol.source.TileJSON({
          url: 'https://api.tiles.mapbox.com/v3/mapbox.20110804-hoa-foodinsecurity-3month.json?secure',
          crossOrigin: 'anonymous'
        })
      })
    ]
});

let map = Map.getMap();
map.addLayer(layer_group);
console.log(layer_group);