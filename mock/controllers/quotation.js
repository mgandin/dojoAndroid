'use strict'

module.exports = function quotationController(app) {

	var quotation = {
	  "description": {
	    "brandCode": "CMD",
	    "travelCode": "15"
	  },
	  "period": {
	    "start": 1412114400000,
	    "end": 1414450800000
	  },
	  "duration": "P27D",
	  "passengers": [
	    {
	      "code": "10",
	      "gender": "Male",
	      "rph": "1",
	      "surname": "surname",
	      "givenName": "givenname"
	    },
	    {
	      "code": "10",
	      "gender": "Male",
	      "rph": "2",
	      "surname": "surname",
	      "givenName": "givenname"
	    }
	  ],
	  "flights": [],
	  "accommodations": [
	    {
	      "period": {
	        "start": 1412114400000,
	        "end": 1414450800000
	      },
	      "rooms": [
	        {
	          "maxAdults": 0,
	          "maxOccupancy": 0,
	          "minOccupancy": 0,
	          "quantity": 0,
	          "cotQuantity": 0,
	          "passengerRPH": [
	            "1",
	            "2"
	          ],
	          "mealPlans": [
	            {
	              "code": "AI",
	              "price": 0
	            }
	          ],
	          "currentAvailability": 0
	        }
	      ],
	      "resortCode": "TURC"
	    }
	  ],
	  "extras": [],
	  "costs": [],
	  "cautions": [],
	  "warnings": [],
	  "transactionIdentifier": "883448",
	  "itSiteCode": "wm08"
	};

	app.post('/package', function(req, res){
		var sleep = (Math.random() * 3) + 1;
		setTimeout(function() {
		  	res.send(quotation);
        }, sleep * 1000);
		console.log("waiting " + sleep + " seconds")
	});
	
};