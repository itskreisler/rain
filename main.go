package main

import "github.com/itskreisler/rain/internal/cli"

var (
	version = "dev"
	commit  = "none"
	date    = "unknown"
)

func main() {
	cli.Execute(cli.BuildInfo{
		Version: version,
		Commit:  commit,
		Date:    date,
	})
}
