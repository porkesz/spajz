Vue.component('add-chamber-component',{
	data: function () {
	    return {
	    	quantity: [],
	    	selectedFoods: [],
	        foods: [],
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Add food to chamber</h1>
			<router-link to="/chamber">Back to chamber</router-link>
		    <br />
		    <span class="error" style="display:none">All fields are required!</span>
		    <br />		    
		        <div class="well">
		            <div class="form-group">
		                <label>Foods</label>
    					<vue-multiselect 
    						v-model="selectedFoods" 
    						:options="foods" 
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
			        <div v-for="(food, index) in selectedFoods">
			            <div class="form-group">
			                <label>{{food.name}} ({{food.unit}})</label>
			                <input type="text" class="form-control" v-model="quantity[index]">
			            </div>
			        </div>    
		        </div>
		        <button type="submit" v-on:click="checkAddFood" class="btn btn-primary">Submit</button>	    
    	</div>
	`,
    methods : {
    	checkAddFood(){
    		var error = false;
    		if (this.selectedFoods.length == 0) {
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
    			this.addFood();
    		}
    	},
    	addFood(){
    		var chamberFood = [];
    		var self = this;
    		
    		$.each(this.selectedFoods, function( index, food ) {
    			chamberFood.push(
    					{
    						foodId: food.id,
    						quantity: self.quantity[index]
    					}
    			);
   		 	});
    		
    		axios({
                method:'post',
                url:'/api/chambers/addFoodsToChamber',
                data: chamberFood
            }).then(function(response){
            	self.$router.push({path: '/chamber'});
            });
    	},
    	fetchOptions(){
    		axios.get("/api/chamber/availableFoods")
            .then(function(response){
            	this.foods = response.data;
            }.bind(this));
    	}
    },
    created: function(){
    	this.fetchOptions();
    }
});
