// 项目全局对象
const app = {
    project : "jizhui", // 项目名称
    host : getHost(),   //表示当前域名 + 端口号
    hostname : document.location.hostname, //表示域名
    href : document.location.href,//表示完整的URL
    port : document.location.port   //表示端口号
    protocol : document.location.protocol   //表示当前的网络协议
    baseuri : protocol+"://"+host+"/"project+"/", // 请求前缀：网路协议://请求域名:端口/项目名称/
}
// 获取请求host
function getHost() {
    var domain = document.domain;
    return (domain == null || domain == "") ? window.location.host : domain;
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