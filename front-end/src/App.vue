<template>

  <div>
    <h4>test</h4>
  </div>

  <div>
    <a> result : {{ chat }}</a>
  </div>

</template>

<script>
import axios from "axios"
export default {
  name: 'App',

  async mounted(){
    let result = await axios.get("http://localhost:8080/gpt/v1/chat/test");
    console.log(result);
    this.chat = result.data;
  },

  data(){
    return {
      chat: "",
    }
  },
  components: {
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
