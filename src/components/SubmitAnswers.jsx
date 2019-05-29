import React, { useState } from 'react';

import InputAction from './InputAction';

export default props => {
    return (
        <InputAction
            id="submit-answers"
            buttonText="Submit Answers"
            disabled={value => !props.canSubmit || !value}
            action={props.submitAnswers}
        />
    );
}
