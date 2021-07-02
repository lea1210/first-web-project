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
import { useRouter } from "vue-router";


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
    const router = useRouter();
    
      //beim Laden der View ausloggen
    doLogout();


    function loginButton(){
      
      doLogin(username.value, password.value).then(value => {
        if(value == true){
           console.log("Value ist true");
           router.push("/");
        }else{
          console.log("Value ist false");
        }
      })
        
      //if(loginstate.isLoggedIn == true){
        //router.push("/");
      //}
    }

    return{
      loginButton, username, password
    }
  }

});
</script>