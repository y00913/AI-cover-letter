<template>
  <body>
    <div id="head">
      <h2>AI 자기소개서</h2>
    </div>

    <form v-on:submit.prevent="ask" id="ask">
      <div>
        <div>
          <textarea v-model="question" placeholder="question" id="question"></textarea>
        </div>

        <div>
          <textarea v-model="information" placeholder="information" id="information"></textarea>
        </div>
      </div>

      <div v-if="!isLoading">
        <button v-on:keyup.enter="submit" id="button">완료</button>
      </div>
    </form>

    <div id="result-box">

      <div id="loading" v-if="isLoading">
        <img src="./assets/loading.gif">
      </div>

      <div id="result" v-if="!isLoading">
        <a> {{ chatGpt }} </a>
      </div>

    </div>

    <div>
      <p style="font-weight: bold; margin-top: 60px">
        made by <a v-bind:href="`https://velog.io/@y00913`" target="_blank" style="text-decoration: none;">y00913</a>
      </p>
    </div>
  </body>
</template>

<script>
import axios from "axios"
export default {
  name: 'App',

  methods: {
    async ask() {
      this.isLoading = true;
      const result = await axios.post("http://218.237.234.37:8000/gpt/v1/chat/msg", {
        question: this.question,
        information: this.information,
      });
      this.chatGpt = result.data;
      this.isLoading = false;
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
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  min-height: 70vh;
  margin: auto;
}

#head {
  text-decoration: none;
  font-size: 1em;
  margin-bottom: 10px;
}

#result-box {
  border-radius: 15px;
  padding: 10px;
  border: 2px solid #b3b0b0;
  margin: 30px;
  width: 1000px;
  height: auto;
  min-height: 300px;
  align-items: center;
  white-space : pre-wrap;
  word-wrap : break-word;
}

#question {
  width: 300px;
  height: 50px;
}

#information {
  width: 300px;
  height: 50px;
}

#button {
  margin-top: 15px;
}

#ask {
  width: 300px;
  height: 180px;
}

#loading {
  margin-top: 100px;
}

#result {
  margin: 20px;
}
</style>
