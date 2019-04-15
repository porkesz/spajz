Vue.component('chamber-component',{
	data: function () {
	    return {
	    	myFoods: [],
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Chamber</h1>
			<router-link to="/add-chamber">Add new food</router-link>
		    <br />
		    <br />
		    <div v-for="food in myFoods">
		    	<ul class="list-group">
		            <li class="list-group-item">
		            	<h4>
		            		{{food.food.name}}
		            		<span class="pull-right">
		            			<router-link v-bind:to="'/edit-chamber/'+food.id"><input class="button-primary" type="submit" value="Edit" /></router-link>
		            			<input class="button-primary" type="submit" v-on:click="deleteFood(food.id)" value="Delete" />
		            		</span>
		            	</h4>
		           	</li>
		            <li class="list-group-item">
		            	<span> {{food.quantity}} {{food.food.unit}}</span>
		            </li>
        		</ul>
		    </div>
    	</div>
	`,
    methods : {
    	deleteFood(id){
    		axios.delete("/api/chambers/" + id)
            .then(function(response){
            	this.fetchMyFoods();
            }.bind(this))
    	},
    	fetchMyFoods(){
    		username = getCookie("login_user");
    		if(username){
    			axios.get("/api/chambersByUser")
                .then(function(response){
                	 this.myFoods = response.data;             	 
                }.bind(this))
    		}
        }
    },
    created: function(){
    	this.fetchMyFoods();
    }
});
