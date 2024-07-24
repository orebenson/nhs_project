document.getElementById('progress-dropdown').addEventListener('change', function() {
    fetch(`/diary/progress?metric=${this.value}`)
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('progress-chart').getContext('2d');
            new Chart(ctx, {
                type: 'line', 
                data: {
                    labels: data.map(entry => entry.date),
                    datasets: [{
                        label: 'Progress',
                        data: data.map(entry => entry.value),
                        fill: false,
                        borderColor: 'rgb(75, 192, 192)',
                        tension: 0.1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        });
});
