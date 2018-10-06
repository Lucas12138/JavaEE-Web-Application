<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Session Error</title>
    </head>
    
	<body>

        <h2>Abstract Action Class Error Message</h2>
        

        <h3 style="color:red"> ${ message } </h3>
        
        <p>
            Normally, in deployment, we would probably send back an
            error code in the HTTP response (like 404 -- Not Found)
            but to facilitate debugging, since you've probably made a
            mistake during development, we're providing the error
            message, above.
        </p>

	</body>
</html>