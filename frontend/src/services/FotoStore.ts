import { Foto } from "./Foto";
import {reactive, readonly} from 'vue';

const fotostate = reactive({
    fotos: Array<Foto>(),
    errormessage: ''
})

export function useFotoStore(){
    return{
        fotostate: readonly(fotostate)
    }

}