import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        profile: frontendData.profile,
        bypasses: frontendData.bypasses,
        undoneReasons: frontendData.undoneReasons,
    }
    }
)