import { useState, useCallback } from 'react';
import { password, username } from "../constants";

const useFetchWrapper = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const sendRequest = useCallback(async (requestConfig, applyData) => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch(requestConfig.url, {
        method: requestConfig.method ? requestConfig.method : 'GET',
        mode: requestConfig.mode ? requestConfig.mode : 'cors',
        headers: requestConfig.headers ? requestConfig.headers : {
            Authorization:
                "Basic " + window.btoa(`${username}:${password}`),
            Accept: "application/json",
            "X-Requested-With": "OpenAPI",
        },
        body: requestConfig.body ? JSON.stringify(requestConfig.body) : null,
      });

      if (!response.ok) {
        throw new Error('Request failed!');
      }

      const data = await response.json();
      applyData(data);
    } catch (err) {
      setError(err.message || 'Something went wrong!');
    }
    setIsLoading(false);
  }, []);

  return {
    isLoading,
    error,
    sendRequest,
  };
};

export default useFetchWrapper;