<template>
  <div class="container">
    <div v-if="fotostate.errormessage != ''">
      <div class="notification is-danger">
        <div>{{fotostate.errormessage}}</div>
      </div>
    </div>
    <!-- Button zum Hinzufügen des nächsten Bildes -->
    <button class="button" v-on:click="geklickt()">
      <i class="fas fa-camera"/>
    </button>
    <!-- Eingabefeld für inkrementelle Suche -->
    <section class="section">
      <input type="text" class="input" placeholder="Suche" v-model="suchwort"/>
    </section>
    <section class="section">
      <div class="columns is-multiline">
        <FotoGalerieBild v-for="i in listitems" v-bind:key="i" :foto="i" @entferne-foto="entferneFoto($event)"/>
      </div>
    </section>
    <div>
      Insgesamt {{fotostate.fotos.length}} Bilder
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, reactive, ref, Ref, onMounted } from "vue";
import FotoGalerieBild from "./FotoGalerieBild.vue";
import { Foto } from "@/services/Foto";
import { fotoliste } from "@/services/FotoListe";
import { useFotoStore } from'@/services/FotoStore';

export default defineComponent({
  name: "FotoGalerie",
  components :{FotoGalerieBild},

  setup(){
    const {fotostate, updateFotos, deleteFoto} = useFotoStore();
    const fotos: Ref<Foto[]> = ref([]);
    const suchwort = ref("");
    let max = fotostate.fotos.length;
    let zaehler = 0;

    const listitems = computed(() => {
      const n: number = suchwort.value.length;
      if(suchwort.value.length < 3) {
        return fotos.value;
      }else{
        return fotos.value.filter(e =>e.ort.toLowerCase().includes(suchwort.value.toLowerCase()));
      }
    });

    function geklickt(){
      if(max<1){
           alert('Keine Fotos mehr')
      }else{
          fotos.value.push(fotostate.fotos[zaehler]);
        zaehler++;
        max--;
      }
    }
    

    function entferneFoto(id:number): void{
      //fotos.value = fotos.value.filter(ele => ele.id !== id);
      deleteFoto(id)
    }

    onMounted(async () =>{
        await updateFotos()
    });


    return {listitems, geklickt, entferneFoto, suchwort, fotostate};

  }

  
});
</script>


<style scoped>

</style>
