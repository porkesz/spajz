window.Event = new Vue({
    data: {
    	isLoggedIn: false
    }
});

Vue.component('navbar-component',{
    template: ` 
    	<div id="app">
		    <nav class="navbar navbar-default">
		    <div class="container">
		      <div class="navbar-header">
		        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		          <span class="sr-only">Toggle navigation</span>
		          <span class="icon-bar"></span>
		          <span class="icon-bar"></span>
		          <span class="icon-bar"></span>
		        </button>
		        <a class="navbar-brand" href="/">MyChamber</a>
		      </div>
		      <div id="navbar" class="collapse navbar-collapse">
		        <ul class="nav navbar-nav">
		          <li></li>
		        </ul>
		        <ul class="nav navbar-nav navbar-right">
		          <li v-if="isLoggedIn()"><a href="/chamber">Chamber</a></li>
		          <li v-if="isLoggedIn()"><a href="/cookbook">Cookbook</a></li>
		          <li v-if="isLoggedIn()"><a v-on:click="logout">Logout</a></li>
		          <li v-show="!isLoggedIn()"><a href="/login">Login</a></li>
		          <li v-show="!isLoggedIn()"><a href="/register">Register</a></li>
		        </ul>
		      </div>
		    </div>
		  </nav>
	  </div>
	`,
    mounted(){
        if(getCookie("login_user")){
            axios.get("/getUsername")
                .then(function(response){
                    window.Event.isLoggedIn = true;
                    Event.$emit('logged-in');
                }.bind(this))
                .catch(function(error){
                    deleteCookie("login_user");
                    return error;
                });
        }
    },
    methods : {
        logout(){
            axios.get("/logout")
                .then(function(response){
                    window.Event.isLoggedIn = false;
                    deleteCookie("login_user");
                    document.location.replace("/");
                }.bind(this))
        },
        isLoggedIn(){
            return window.Event.isLoggedIn;
        }
    }
});
