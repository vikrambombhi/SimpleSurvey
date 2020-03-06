function addQuestion() {
        var node = document.createElement('div')
        var label = document.createElement('label')
        var select = document.createElement('select')
        label.innerHTML = 'Question'
        node.appendChild(label)

        select.innerHTML='<option value="range">Range</option><option value="option">Option</option><option value="text">Text</option>'
        node.appendChild(select)

        document.getElementById('newSurveyForm').appendChild(node)
}

async function loadSurveyList() {
    const surveyListDom = document.getElementById('survey-list');
    const response = await fetch("http://localhost:8080/surveys");
    const json = await response.json();

    json['_embedded'].survey.forEach((survey) => {
        const li = document.createElement("li");
        li.className = 'Polaris-List__Item';
        li.innerHTML = survey.name;
        surveyListDom.appendChild(li);
    });
}

window.addEventListener('DOMContentLoaded', async (event) => {
    await loadSurveyList();
});