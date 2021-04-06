var exec = require('cordova/exec');

exports.initialize = function (cptoken, cpgroupname, cpname, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'initialize', [cptoken,cpgroupname,cpname]);
};

exports.askforregistration = function (token, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'askforregistration', [token]);
};

exports.doPostToken = function (arg0, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'doPostToken', [arg0]);
};

exports.pageView = function (utmdt, utmp, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'pageView', [utmdt, utmp]);
};

exports.productView = function (pName,pSku,utmp, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'productView', [pName,pSku,utmp]);
};

exports.add2Cart = function (pName, pSku, pQty, pUnitPrice, utmp, pimg, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'add2Cart', [pName, pSku, pQty, pUnitPrice, utmp, pimg]);
};

exports.removeFromCart = function (pName, pSku, pQty, pUnitPrice, utmp, pimg, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'removeFromCart', [pName, pSku, pQty, pUnitPrice, utmp, pimg]);
};

exports.setOrderData = function (oId, oValue, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'setOrderData', [oId, oValue]);
};

exports.addOrderItem = function (sku, name, quant, price, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'addOrderItem', [sku, name, quant, price]);
};

exports.placeOrder = function (utmp, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'placeOrder', [utmp]);
};

exports.postCart = function (arg0, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'postCart', [arg0]);
};

exports.postCustomCart = function (customCarts, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'postCustomCart', [customCarts]);
};

exports.postContactEmail = function (cp_curEmail, utmp, success, error) {
    exec(success, error, 'fbsdkconsumerplugin', 'postContactEmail', [cp_curEmail, utmp]);
};
