Vue.component('edit-recipe-component',{
	data: function () {
	    return {
	    	recipe: {},
	    	quantity: [],
	    	value: [],
	        options: [],
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Edit Recipe</h1>
			<router-link to="/cookbook">Back to cookbook</router-link>
		    <br />
		    <span class="error" style="display:none">All fields are required!</span>
		    <br />		    
		        <div class="well">
		            <div class="form-group">
		                <label>Recipe Name</label>
		                <input type="text" class="form-control" placeholder="Recipe Name" v-model="recipe.name">
		            </div>
		            <div class="form-group">
		                <label>Description</label>
		                <input type="text" class="form-control" placeholder="Description" v-model="recipe.description">
		            </div>
		        </div>
		        <div class="well">
		            <div class="form-group">
		                <label>Component</label>
    					<vue-multiselect 
    						v-model="value"
    						:options="options"
    						:value="value" 
    						:multiple="true" 
    						:close-on-select="false" 
    						:clear-on-select="false" 
    						:preserve-search="true" 
    						placeholder="Pick some" 
    						label="name" 
    						track-by="name" 
    						:preselect-first="true">
    							<template 
    								slot="selection" 
    								slot-scope="{ values, search, isOpen }">
    									<span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">
    										{{ values.length }} options selected
    									</span>
    							</template>
    					</vue-multiselect>
		            </div>
		        </div>
		        <div class="well" id="component-unit">
			        <div v-for="(component, index) in value">
			            <div class="form-group">
			                <label>{{component.name}} ({{component.unit}})</label>
			                <input type="text" class="form-control" v-model="quantity[index]">
			            </div>
			        </div>    
		        </div>
		        <button type="submit" v-on:click="checkAddRecipe" class="btn btn-primary">Submit</button>	    
    	</div>
	`,
    methods : {
    	checkAddRecipe(){
    		var error = false;
    		if (this.recipe.name == undefined
    			|| this.recipe.description == undefined
    			|| this.value.length == 0) {
    			error = true;
    		}
    		
    		$.each(this.quantity, function( index, value ) {
    			if (value == 'undefined'){
    				error = true;
    			}
   		 	});
    		
    		if (error == true) {
    			$('.error').show();
    		} else {
    			this.addRecipe();
    		}
    	},
    	addRecipe(){
    		var recipeFood = [];
    		var self = this;
    		
    		$.each(this.value, function( index, val ) {
    			recipeFood.push(
    					{
    						foodId: val.id,
    						quantity: self.quantity[index]
    					}
    			);
   		 	});
    		
    		axios({
                method:'put',
                url:'/api/recipes/' + self.$route.params.id,
                data: { name: this.recipe.name, description: this.recipe.description, foods: recipeFood }
            }).then(function(response){
            	self.$router.push({path: '/cookbook'});
            });
    	},
    	fetchOptions(){
    		axios.get("/api/foods")
            .then(function(response){
            	this.options = response.data;
            }.bind(this));
    	},
    	fetchRecipe(){
    		var id = this.$route.params.id;
    		axios.get("/recipes/" + id)
            .then(function(response){
            	var data = response.data;
            	this.recipe = {name: data.name, description: data.description};
            }.bind(this))
        },
        fetchComponents(){
        	var id = this.$route.params.id;
        	axios.get("/recipeFoodsByRecipeId/" + id)
            .then(function(response){
            	var tempFood = [];
            	var tempQuantity = [];
            	$.each(response.data, function( index, data ) {
        			tempFood.push(data.food);
        			tempQuantity[index] = data.quantity;
       		 	});
            	this.value = tempFood;
            	this.quantity = tempQuantity;
            }.bind(this));
        }
    },
    created: function(){
    	this.fetchOptions();
    	this.fetchRecipe();
    	this.fetchComponents();
    }
});
