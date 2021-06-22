import { Foto } from "./Foto";
import {reactive, readonly} from 'vue';

const fotostate = reactive({
    fotos: Array<Foto>(),
    errormessage: ''
})

export function useFotoStore(){
    return{
        fotostate: readonly(fotostate),
        updateFotos,
        deleteFoto
    }

}

function updateFotos(){
    fetch('api/foto')
    .then((response) =>{
        if(!response.ok){
            fotostate.errormessage = response.statusText
        }else{
            fotostate.errormessage = ''
            return response.json()
        }
    })
    .then((jsondata)=>{
        fotostate.fotos = jsondata
    })
    .catch(reason => {
        fotostate.errormessage = reason
    });
}

function deleteFoto(id:number){
    fetch('api/foto/'+id,{
        method: 'DELETE'
    })
    .then((response) =>{
        if(!response.ok){
            fotostate.errormessage = response.statusText
        }else{
            fotostate.errormessage = ''
            return response.json()
        }
    })
    .catch(reason => {
        fotostate.errormessage = reason
    });
}