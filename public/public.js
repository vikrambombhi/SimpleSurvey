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
