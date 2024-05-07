
while ($true) {

    $csvPath = "C:\docker_stats_history.csv"

    try {
        $stats = docker stats --no-stream --format "{{.Container}},{{.CPUPerc}},{{.MemUsage}},{{.NetIO}},{{.BlockIO}},{{.PIDs}}"
        if (-not $stats) {
            throw "Não foi possível obter estatísticas do Docker."
        }

        Add-Content -Path $csvPath -Value $stats
    } catch {
        Write-Error "Erro: $_"
    }

    Start-Sleep -Seconds 60
}