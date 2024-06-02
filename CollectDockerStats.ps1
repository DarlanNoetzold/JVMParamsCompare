while ($true) {
    $csvPath = "C:\docker_stats_history.csv"
    try {
        $stats = docker stats --no-stream --format "{{.Container}},{{.CPUPerc}},{{.MemUsage}},{{.NetIO}},{{.BlockIO}},{{.PIDs}}"
        if (-not $stats) {
            throw "Error to get Docker"
        }
        Add-Content -Path $csvPath -Value $stats
    } catch {
        Write-Error "Error: $_"
    }
    Start-Sleep -Seconds 60
}