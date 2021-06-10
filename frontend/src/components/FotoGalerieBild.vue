<template>
  <div class="card column is-3 has-background-grey-lighter m-3">
    <div class="card-header">
      <p class="card-header-title is-centered">
        <!-- Dateinamen ausgeben -->
        {{dateiname}}
      </p>
      <!-- Lösch-Button -->
      <button class="button card-header-icon has-background-grey-light" @click="delclicked()">
        <i class="fa fa-times" />
      </button>
    </div>
    <div class="card-content has-text-centered">
      <!-- Bild anzeigen -->
      <figure class="image is-inline-block">
        <img :src="url" />
      </figure>
      <div class="content">
        <foto-star-rating :maxsterne="5" />
      </div>
      <!-- Ort -->
      <div class="content">{{ort}}</div>
      <!-- Zeitstempel -->
      <div class="has-text-grey">{{zeitstempel}}</div>
    </div>
  </div>
</template>


<script lang="ts">
import { defineComponent, PropType, ref } from "vue";
import FotoStarRating from "./FotoStarRating.vue";
import { Foto } from "@/services/Foto";

export default defineComponent({
  components: { FotoStarRating },
  name: "FotoGalerieBild",
  props: {
        foto: {type: Object as PropType<Foto>}
  },
  setup(props, context) {

    function delclicked():void{
      context.emit("entferne-foto", props.foto?.id);
    }

    
    return {
      url: require("@/assets/thumbnails/" + props.foto?.dateiname),
      dateiname:props.foto?.dateiname,
      zeitstempel: props.foto?.zeitstempel,
      ort:props.foto?.ort, 
      delclicked

    };
  }
});
</script>
