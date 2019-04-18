Vue.component('menu-component',{
	data: function () {
	    return {
	    	menus: [],
	    	shoppingList: [],
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Menu</h1>
			<router-link to="/chamber">Back to chamber</router-link>
		    <br />
		    <br />
		    <div v-for="menu in menus">
			    <ul class="list-group">
		            <li class="list-group-item">
			            <h5>
			            	{{ menu.recipe.name }}
			            	<input class="button-primary pull-right" type="submit" v-on:click="deleteMenu(menu.id)" value="Delete" />
			            </h5>
		            </li>
	        	</ul>
        	</div>
        	<br />
        	<br />
        	<h2>Shopping list<h2>
        	<br />
        	<ul class="list-group">
		            <li class="list-group-item">
		            	<div v-for="listItem in shoppingList">
		            		<h6>{{ listItem.foodName }}: {{ listItem.foodQuantity }} {{ listItem.foodUnit }}</h6>
			            </div>
		            </li>
	        	</ul>
    	</div>
	`,
    methods : {
    	fetchMenus(){
    		axios.get("/api/menusByUser")
            .then(function(response){
            	this.menus = response.data;
            }.bind(this))
        },
        fetchShoppingList(){
        	axios.get("/api/shoppingList")
            .then(function(response){
            	this.shoppingList = response.data;
            }.bind(this))
        },
        deleteMenu(id){
        	axios.delete("/api/menus/" + id)
            .then(function(response){
            	this.fetchMenus();
            	this.fetchShoppingList();
            }.bind(this))
        }
    },
    created: function(){
        this.fetchMenus();
        this.fetchShoppingList();
    }
});
