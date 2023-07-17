<template>
  <div>
    <h2>ai 자기소개서</h2>
  </div>

  <form v-on:submit.prevent="ask">
    <div>
      <textarea v-model="question" placeholder="question"></textarea>
    </div>

    <div>
      <textarea v-model="information" placeholder="information"></textarea>
    </div>

    <div v-if="!isLoading">
      <button v-on:keyup.enter="submit">완료</button>
    </div>
  </form>

  <br />
  <br />

  <div id="loading" style="margin-top: 25px" v-if="isLoading">
    <img src="./assets/loading.gif">
  </div>

  <div v-if="!isLoading">
    <a> {{ chatGpt }} </a>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'App',

  methods: {
    async ask() {
      this.isLoading = true;
      let result = await axios.post("http://218.237.234.37:8000/gpt/v1/chat/msg", {
        question: this.question,
        information: this.information,
      });
      this.chatGpt = result.data;
      this.isLoading = false;

      console.log(this.chatGpt);
    },
  },

  data() {
    return {
      question: "",
      information: "",
      chatGpt: "",
      isLoading: false,
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

.result {

}

</style>
