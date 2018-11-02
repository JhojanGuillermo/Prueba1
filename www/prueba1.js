function Prueba1(){}

Prueba1.prototype.show = function (base64, successCallback, errorCallback){
    var options = {};
    options.text = base64;
    cordova.exec(successCallback, errorCallback, 'Prueba1', 'show', [options]);
}

Prueba1.install = function() {
    if(!window.plugins){
        window.plugins = {};
    }
    window.plugins.prueba1 = new Prueba1();
    return window.plugins.prueba1;
};
cordova.addConstructor(ToastyPlugin.install);