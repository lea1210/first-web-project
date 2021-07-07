<template>
  <div>
    <div>
      <p>
        <!-- Dateinamen und Zeitstempel ausgeben -->
        {{foto.dateiname}}
        {{foto.zeitstempel}}
      </p>
    </div>
    <div>
      <!-- Bild anzeigen -->
      <img :src="url" />
      <!-- Ort -->
      <div class="content">{{foto.ort}}</div>
    </div>
    <router-link to="/">zurück</router-link>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useFotoStore } from'@/services/FotoStore';

export default defineComponent({
  name: "FotoDetailView",
  components: {
  },
  props: {
      fotoid: {type: String}
  },
  setup(props){
    const {fotostate} = useFotoStore();
    let foto  = undefined;
    console.log("fotos: " +fotostate.fotos);
    if(props.fotoid != undefined){
        const idNumber = parseInt(props.fotoid);
        console.log("id: " + idNumber);
        foto = fotostate.fotos.find(e =>e.id == idNumber);
        console.log("Foto gefunden :" );
    }
    return{
        foto,
        url: "/api/foto/" + foto?.id
    }

      
  }
});
</script>