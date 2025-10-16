<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Restwo Login</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/assets/css/uistyle.css" rel="stylesheet" />
</head>
<body>
  <div class="bg">
    <div class="ellipse-right"></div>
    <div class="curve-lines">
      <svg viewBox="0 0 1200 300" preserveAspectRatio="none" >
        <path d="M0,80 C400,10 800,40 1200,20" />
        <path d="M0,120 C400,50 800,60 1200,50" />
      </svg>
    </div>
  </div>
  <div class="container">
    <div class="login-box">
      <div class="logo">Restwo.</div>
      <h2>Sign In</h2>
      <p>Sign in to stay connected.</p>
      <input type="text" placeholder="Username" />
      <input type="password" placeholder="Password" />
      <div class="options">
        <a href="#">Forgot Password</a>
      </div>
      <button>Sign in</button>
      <div class="signup">
        Don?t have an account? <a href="${pageContext.request.contextPath}/register">Click here to sign up.</a>
      </div>
    </div>
  </div>
</body>
</html>