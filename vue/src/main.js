import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from '../router/index.js'
import store from './store/index'
import request from "@/utils/request";

// 引入video插件
import VideoPlayer from 'vue-video-player'

Vue.use(VideoPlayer)

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.axios = request

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
