<template>
  <div id="app">
    <submit></submit>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import submit from "@/components/submit";

export default {
  name: 'App',
  components: {
    // HelloWorld
    submit
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
  margin-top: 60px;
}
</style>
