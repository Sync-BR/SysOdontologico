document.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.getElementById('consultsTable');
    const rows = Array.from(tableBody.querySelectorAll('tr'));

    // Obtém a data atual no formato "yyyy-MM-dd"
    const today = new Date().toISOString().split('T')[0];

    // Função para converter "dd/MM/yyyy HH:mm" para Date
    function parseDate(dateStr) {
        const [day, month, year, time] = dateStr.split(/\/| /);
        const [hours, minutes] = time.split(':');
        return new Date(year, month - 1, day, hours, minutes);
    }

    // Filtra as consultas que são hoje (data igual a "today")
    const filteredRows = rows.filter(row => {
        const consultDateStr = row.cells[5].innerText.trim(); // Pegando a coluna da data
        const consultDay = consultDateStr.split(' ')[0]; // Data sem hora (ex: "29/12/2024")
        return formatDateToISO(consultDay) === today; // Compara com a data de hoje
    });

    // Ordena as consultas pela data (e hora)
    const sortedRows = filteredRows.sort((a, b) => {
        const dateA = parseDate(a.cells[5].innerText.trim()); // Conversão da data para Date
        const dateB = parseDate(b.cells[5].innerText.trim());
        return dateA - dateB; // Ordenação crescente
    });

    // Limpa a tabela original
    tableBody.innerHTML = '';

    // Adiciona as linhas filtradas e ordenadas
    sortedRows.forEach(row => tableBody.appendChild(row));

    // Converte "dd/MM/yyyy" para "yyyy-MM-dd"
    function formatDateToISO(dateStr) {
        const [day, month, year] = dateStr.split('/');
        return `${year}-${month}-${day}`; // Formato ISO "yyyy-MM-dd"
    }
});