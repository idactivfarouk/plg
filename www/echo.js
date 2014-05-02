var echo =  
{
    affiche: function(message, successCallback, errorCallback) 
	{
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Echo', // mapped to our native Java class called "Calendar"
            'echo', // with this action name
            [message]
        );
    }
}
module.exports = echo;