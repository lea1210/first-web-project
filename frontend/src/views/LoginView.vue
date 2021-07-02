<template>
  <div>
    <div>
        <div>Username</div>
        <input type="text" v-model="username">
    </div>
     <div>
        <div>Passwort</div>
        <input type="text" v-model="password">
    </div>
    <div>
        <button class="button" v-on:click="loginButton()">Login</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from "vue";
import { useLoginStore } from '@/services/LoginStore';


export default defineComponent({
  name: "LoginView",
  components: {
  },

  props: {
      
  },

  setup(props){
    const {loginstate, doLogin,doLogout} = useLoginStore();
    const username = ref("");
    const password = ref("");
    
      //beim Laden der View ausloggen
    doLogout();


    function loginButton(){
      doLogin(username.value, password.value);
    }

    return{
      loginButton, username, password
    }
  }

});
</script>