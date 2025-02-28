require('dotenv').config();
const { Client, logger } = require('camunda-external-task-client-js');

// Configuration for the Client
const config = {
  baseUrl: process.env.CAMUNDA_REST_URL,
  workerId: process.env.CAMUNDA_WORKER_ID,
  maxTasks: 1,
  asyncResponseTimeout: 10000,
  use: logger
};

// Create a Client instance
const client = new Client(config);

// Subscribe to the topic
client.subscribe(process.env.CAMUNDA_TOPIC_NAME, async function({ task, taskService }) {
  try {
    // Get process variables
    const processVariables = task.variables.getAll();
    console.log('Processing task:', task.id);
    console.log('Process variables:', processVariables);

    // Add your business logic here
    // For example:
    const result = {
      status: 'success',
      message: 'Task processed successfully'
    };

    // Complete the task
    await taskService.complete(task, {
      resultStatus: { value: result.status },
      resultMessage: { value: result.message }
    });

    console.log('Task completed successfully');
  } catch (error) {
    console.error('Error processing task:', error);
    
    // Handle task failure
    await taskService.handleFailure(task, {
      errorMessage: error.message,
      errorDetails: error.stack,
      retries: 0,
      retryTimeout: 0
    });
  }
});