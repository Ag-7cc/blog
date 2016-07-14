/**
 * 系统常量
 * @type {{baseUrl: string}}
 */
window.Blog = {
    BASE_URL: 'http://192.168.0.103'
};

/**
 * 字符串格式化
 * @type {String.f}
 */
String.prototype.format = String.prototype.f = function () {
    var s = this, i = arguments.length;
    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};

/**
 * 组装请求URL
 * @type {{getUrl: Request.getUrl}}
 */
var Request = {
    getUrl: function (uri, params) {
        var timestamp = new Date().getTime();
        return isEmpty(params) ? '{0}?timestamp={1}'.format(uri, timestamp) : '{0}?timestamp={1}&{2}'.format(uri, timestamp, params);
    }
};

/**
 *判断是否为空 true 为空，false，为有值
 * @param val
 * @returns {boolean}
 */
var isEmpty = function (val) {
    return undefined === val || null === val || val === "" || val === "null" || val === "undefined";
};

/**
 * 判断是否为空 true 为空，false，为有值
 * @param val
 * @returns {boolean}
 */
var notEmpty = function (val) {
    return !isEmpty(val);
};

/**
 * 去除两边空格
 * @param v 值
 * @returns {XML|string|*|void}
 */
var trim = function (v) {
    return v.replace(/(^\s*)|(\s*$)/g, "");
};