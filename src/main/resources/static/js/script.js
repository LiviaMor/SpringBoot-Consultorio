// Função auxiliar para validar a resposta do fetch
const checkResponse = response => {
    if (!response.ok) {
        throw new Error(`Erro na requisição: ${response.statusText}`);
    }
    return response.json();
};

// Função genérica para enviar dados via POST
const postData = (url, data) =>
    fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(checkResponse);

// Função para carregar e exibir a lista de consultórios
const loadConsultorios = () => {
    fetch('http://localhost:8080/consultorio')
        .then(checkResponse)
        .then(consultorios => {
            const consultoriosList = document.getElementById('consultoriosList');
            if (consultoriosList) {
                consultoriosList.innerHTML = consultorios
                    .map(consultorio => `<li>${consultorio.nome} - ${consultorio.endereco}</li>`)
                    .join('');
            }
        })
        .catch(error => console.error('Erro ao carregar consultórios:', error));
};

// Função para carregar e exibir a lista de pacientes
const loadPacientes = () => {
    fetch('http://localhost:8080/pacientes')
        .then(checkResponse)
        .then(pacientes => {
            const pacientesList = document.getElementById('pacientesList');
            if (pacientesList) {
                pacientesList.innerHTML = pacientes
                    .map(paciente => `<li>${paciente.nome} - ${paciente.cpf}</li>`)
                    .join('');
            }
        })
        .catch(error => console.error('Erro ao carregar pacientes:', error));
};

// Função para carregar e exibir a lista de valores diários do caixa
const loadCaixa = (date) => {
    fetch(`http://localhost:8080/caixa/byDate/${date}`)
        .then(checkResponse)
        .then(caixaEntries => {
            const caixaList = document.getElementById('caixaList');
            if (caixaList) {
                caixaList.innerHTML = caixaEntries
                    .map(entry => `<li>${entry.data} - ${entry.descricao}: R$ ${entry.valor.toFixed(2)}</li>`)
                    .join('');
            }
        })
        .catch(error => console.error('Erro ao carregar valores do caixa:', error));
};

// Função para carregar e exibir a lista de consultas
const loadConsultas = () => {
    fetch('http://localhost:8080/consultas')
        .then(checkResponse)
        .then(consultas => {
            const consultasList = document.getElementById('consultasList');
            if (consultasList) {
                consultasList.innerHTML = consultas
                    .map(consulta => `<li>${consulta.data} - ${consulta.procedimento}: R$ ${consulta.valor.toFixed(2)}</li>`)
                    .join('');
            }
        })
        .catch(error => console.error('Erro ao carregar consultas:', error));
};

// Event listener para cadastro de consultório
document.getElementById('consultorioForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const consultorio = Object.fromEntries(formData);

    postData('http://localhost:8080/consultorio', consultorio)
        .then(data => {
            alert('Consultório cadastrado com sucesso!');
            this.reset();
            loadConsultorios();
        })
        .catch(error => console.error('Erro ao cadastrar consultório:', error));
});

// Event listener para cadastro de paciente
document.getElementById('pacienteForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const paciente = Object.fromEntries(formData);

    postData('http://localhost:8080/pacientes', paciente)
        .then(data => {
            alert('Paciente cadastrado com sucesso!');
            this.reset();
            loadPacientes();
        })
        .catch(error => console.error('Erro ao cadastrar paciente:', error));
});

document.addEventListener('DOMContentLoaded', () => {
    // Carrega a lista de consultórios no dropdown ao carregar a página
    loadConsultoriosDropdown();
    // Carrega a lista de pacientes ao carregar a página
    loadPacientes();

// Event listener para cadastro de anamnese e vinculação ao paciente
document.getElementById('anamneseForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const anamnese = Object.fromEntries(formData);
    const pacienteId = anamnese.pacienteId;

    postData(`http://localhost:8080/pacientes/${pacienteId}/anamnese`, anamnese)
        .then(data => {
            alert('Anamnese cadastrada com sucesso!');
            this.reset();
        })
        .catch(error => console.error('Erro ao cadastrar anamnese:', error));
});

// Event listener para cadastro de consulta e vinculação ao paciente
document.getElementById('consultaForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const consulta = Object.fromEntries(formData);
    const pacienteId = consulta.pacienteId;

    postData(`http://localhost:8080/consultas`, consulta)
        .then(data => {
            alert('Consulta cadastrada com sucesso!');
            this.reset();
        })
        .catch(error => console.error('Erro ao cadastrar consulta:', error));
});

// Event listener para consulta de valores diários do caixa
document.getElementById('caixaForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const date = formData.get('date');

    loadCaixa(date);
});

document.addEventListener('DOMContentLoaded', () => {
    // Carrega as listas de consultórios e pacientes ao carregar a página
    loadConsultorios();
    loadPacientes();
});
