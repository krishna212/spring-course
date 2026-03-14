
import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    const fetchContent = async () => {
      try {
        const response = await fetch('/api/content')
        if (!response.ok) {
          throw new Error('Failed to load content')
        }
        const data = await response.json()
        console.log('content json:', data)
        setItems(data)
      } catch (fetchError) {
        setError(fetchError.message)
      } finally {
        setLoading(false)
      }
    }

    fetchContent()
  }, [])

  return (
    <main className="app">
      <h1>Spring Course Content</h1>

      {loading && <p>Loading content...</p>}
      {error && <p className="error">{error}</p>}

      {!loading && !error && (
        <ul className="content-list">
          {items.map((item) => (
            <li key={item.id} className="content-card">
              <h2>{item.title}</h2>
              <p>{item.desc}</p>
              <div className="meta-row">
                <span>{item.status}</span>
                <span>{item.contentType}</span>
              </div>
              {item.url && (
                <p>
                  <a href={item.url} target="_blank" rel="noreferrer">
                    {item.url}
                  </a>
                </p>
              )}
            </li>
          ))}
        </ul>
      )}
    </main>
  )
}

export default App
