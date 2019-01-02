
const myobj = {
}
const url_m = {
    // prefic: "http://211.149.179.228/borrow"
   // prefic : "http://localhost/borrow"
   prefic : "http://"+getHost()+"/borrow"
}
function getHost() {
    var host = "";
    host = (document.domain == null || document.domain == "") ? window.location.host : document.domain;
    return host;
}
/**
 * 获取请求参数
 * @param name
 * @returns {*}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
/**
 * 获取指定URL的参数值
 * @param url  指定的URL地址
 * @param name 参数名称
 * @return 参数值
 */
function getUrlParam(url,name) {
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    var matcher = pattern.exec(url);
    var items = null;
    if(null != matcher){
        try{
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        }catch(e){
            try{
                items = decodeURIComponent(matcher[1]);
            }catch(e){
                items = matcher[1];
            }
        }
    }
    return items;
}