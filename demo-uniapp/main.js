import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

import constant from '@/js/constant.js'
import api from '@/js/api.js'
import utils from '@/js/utils.js'
import store from '@/js/store.js'

Vue.prototype.$store = store;
Vue.prototype.$api = api;
Vue.prototype.$utils = utils;
Vue.prototype.$constant = constant;

import top from '@/components/my/top.vue'
import cnav from '@/components/my/cnav.vue'
import cnull from '@/components/my/cnull.vue'

Vue.component('top', top)
Vue.component('cnav', cnav)
Vue.component('cnull', cnull)


import filter from '@/js/filter.js'
Object.keys(filter).forEach(key => Vue.filter(key, filter[key]));

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
