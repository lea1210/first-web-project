<template>
  <section id="app">
    <div class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">MI Foto Community</h1>
        <div>{{loginstate.username}}</div>
      </div>
    </div>

    <div class="block">
      <nav class="navbar">
        <div class="navbar-menu is-active">
          <router-link to="/" class="navbar-item">Übersicht</router-link>
          <router-link to="/about" class="navbar-item">About</router-link>
        </div>
        <div class="navbar-end">
          <div class="navbar-item">
            <a href="/login" class="navbar-item">Logout</a>
            <router-link to="/login"></router-link>
          </div>
        </div>
      </nav>
    </div>

    <div class="block">
      <div class="container">
          <router-view />
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useLoginStore } from '@/services/LoginStore';

export default defineComponent({
  name: "App",
  components: {},
  setup(){
     const {loginstate} = useLoginStore();

     return{
       loginstate
     }
  }
});
</script>
