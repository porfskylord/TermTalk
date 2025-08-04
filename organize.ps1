# Create necessary directories
$clientJavaDir = "client\src\main\java\com\lordscave\client"
$serverJavaDir = "server\src\main\java\com\lordscave\server"
$sharedJavaDir = "shared\src\main\java\com\lordscave\shared"
$serverResourcesDir = "server\src\main\resources"

# Create directories if they don't exist
New-Item -ItemType Directory -Force -Path $clientJavaDir | Out-Null
New-Item -ItemType Directory -Force -Path $serverJavaDir | Out-Null
New-Item -ItemType Directory -Force -Path $sharedJavaDir | Out-Null
New-Item -ItemType Directory -Force -Path $serverResourcesDir | Out-Null

# Move client files
Move-Item -Path "src\main\java\com\lordscave\client\*.java" -Destination $clientJavaDir -Force

# Move server files
Move-Item -Path "src\main\java\com\lordscave\server\*.java" -Destination $serverJavaDir -Force

# Move shared files
Move-Item -Path "src\main\java\com\lordscave\shared\*.java" -Destination $sharedJavaDir -Force

# Move resources
if (Test-Path "src\main\resources") {
    Move-Item -Path "src\main\resources\*" -Destination $serverResourcesDir -Force
}

Write-Host "Project has been reorganized successfully!"
