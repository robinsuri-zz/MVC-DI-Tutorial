<html>
<head>
    <title>Create TODO</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/view.js"></script>
  </head>
<body>
	  Name: <b>${name}</b><br>
      Email Address: <b>${email}</b><br>
      TODO items: <b>${todo}</b><br><br>
      <button id="submit" type="button">Edit TODO</button>
      <div style="visibility:hidden" id="userData" data-name="${name}" data-email="${email}" data-todo="${todo}"/>
</body>
</html>