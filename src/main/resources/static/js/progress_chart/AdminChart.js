document.addEventListener('DOMContentLoaded', function () {
    const userId = document.body.dataset.userid;
    const selectElement = document.getElementById('progress-dropdown');
    const ctx = document.getElementById('chartCanvas').getContext('2d');
    let chartInstance = null;

    selectElement.addEventListener('change', function () {
        fetch(`/admin/progress?userId=${userId}&metric=${this.value}`)
            .then(response => response.json())
            .then(data => {
                const labels = data.map(entry => entry.date);
                const values = data.map(entry => entry.value);

                if (chartInstance) {
                    chartInstance.destroy();
                }

                chartInstance = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: this.value.charAt(0).toUpperCase() + this.value.slice(1),
                            data: values,
                            fill: false,
                            borderColor: 'rgb(0, 82, 204)', // NHS Blue
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
            })
            .catch(error => console.error('Error loading the data:', error));
    });

    selectElement.dispatchEvent(new Event('change'));
});
