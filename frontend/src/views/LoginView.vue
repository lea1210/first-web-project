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
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "LoginView",
  components: {
  },

  props: {
      
  },

  setup(props){
  
      
  }
});
</script>