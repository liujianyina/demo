
var isReady=false;var onReadyCallbacks=[];
var __uniConfig = {"pages":["pages/login","pages/register","pages/product/product","pages/user/user","pages/paper/paper","pages/order/order","pages/product/buy","pages/user/tixian","pages/user/kefu"],"window":{"bounce":"none","titleNView":false,"softinputNavBar":"none","scrollIndicator":"none"},"tabBar":{"color":"#8a8a8a","selectedColor":"#FAB116","borderStyle":"#212024","backgroundColor":"#212024","list":[{"pagePath":"pages/product/product","iconPath":"/static/product.png","selectedIconPath":"/static/product_active.png","text":"商品行情"},{"pagePath":"pages/order/order","iconPath":"/static/order.png","selectedIconPath":"/static/order_active.png","text":"交易记录"},{"pagePath":"pages/paper/paper","iconPath":"/static/paper.png","selectedIconPath":"/static/paper_active.png","text":"财经快报"},{"pagePath":"pages/user/user","iconPath":"/static/user.png","selectedIconPath":"/static/user_active.png","text":"个人账户"}]},"splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"demo","compilerVersion":"2.5.1","entryPagePath":"pages/login","networkTimeout":{"request":6000,"connectSocket":6000,"uploadFile":6000,"downloadFile":6000}};
var __uniRoutes = [{"path":"/pages/login","meta":{"isQuit":true},"window":{}},{"path":"/pages/register","meta":{},"window":{}},{"path":"/pages/product/product","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/user/user","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/paper/paper","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/order/order","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/product/buy","meta":{},"window":{}},{"path":"/pages/user/tixian","meta":{},"window":{}},{"path":"/pages/user/kefu","meta":{},"window":{}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes}}}});