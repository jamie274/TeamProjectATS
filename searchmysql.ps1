# Search for all paths to mysql.exe and store them in an array
$paths = Get-ChildItem -Path 'C:\' -Filter 'mysql.exe' -Recurse -ErrorAction SilentlyContinue | Select-Object -ExpandProperty DirectoryName

# Output the paths
$paths