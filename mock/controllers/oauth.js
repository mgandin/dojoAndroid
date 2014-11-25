'use strict'

module.exports = function oauthController(app) {

	var request_token = "oauth_token=fa04928e-e7bb-4c0a-a747-a3a39927c68c&oauth_token_secret=8XiCOLLxZiJdbQo8wMz45zhLIf9Op3eLJx%2FDNoeWdAtq6ZNQaau9GyorlGVCbDSCb0NO26q4JF3ee6%2FJ%2BtFYJ%2B2mOuKVJhgW9z%2FxteUkVbg%3D";
	var access_token = "oauth_token=a9d1e29a-67cf-4fe4-acd3-8eeb20c642ee&oauth_token_secret=EtUYTdFLkMdA0%2BoDRL%2FAxZlKHWppCfNDzYVljxrcszEH8CmOci5w0Q7ZMDO7Q4WQ%2FTV68hZgoixb%2FNXnw%2FHMykNQIqFKWpX2caIPBnPAYhg%3D";
	
	app.get('/request_token', function(req, res){
		var sleep = (Math.random() * 2) + 1;
		setTimeout(function() {
		  	res.send(request_token);
        }, sleep * 1000);
		console.log("waiting " + sleep + " seconds")
	});

	app.get('/access_token', function(req, res){
		var sleep = (Math.random() * 2) + 1;
		setTimeout(function() {
		  	res.send(access_token);
        }, sleep * 1000);
		console.log("waiting " + sleep + " seconds")
	});
	
};